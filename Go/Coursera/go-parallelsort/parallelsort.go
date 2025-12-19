package main

import (
	"bufio"
	"fmt"
	"os"
	"sort"
	"strconv"
	"strings"
	"sync"
)

func sortPartition(partition []int, id int, wg *sync.WaitGroup) {
	defer wg.Done()
	fmt.Printf("Goroutine %d sorting: %v\n", id, partition)
	sort.Ints(partition)
}

func merge(a, b []int) []int {
	result := make([]int, 0, len(a)+len(b))
	i, j := 0, 0

	for i < len(a) && j < len(b) {
		if a[i] <= b[j] {
			result = append(result, a[i])
			i++
		} else {
			result = append(result, b[j])
			j++
		}
	}

	result = append(result, a[i:]...)
	result = append(result, b[j:]...)

	return result
}

func main() {
	fmt.Print("Enter integers separated by spaces: ")

	scanner := bufio.NewScanner(os.Stdin)
	scanner.Scan()
	input := scanner.Text()

	parts := strings.Fields(input)
	numbers := make([]int, 0, len(parts))

	for _, part := range parts {
		num, err := strconv.Atoi(part)
		if err != nil {
			fmt.Println("Invalid input:", part)
			return
		}
		numbers = append(numbers, num)
	}

	n := len(numbers)
	partitionSize := n / 4

	partitions := make([][]int, 4)
	for i := 0; i < 4; i++ {
		start := i * partitionSize
		end := start + partitionSize
		if i == 3 {
			end = n
		}
		partitions[i] = numbers[start:end]
	}

	var wg sync.WaitGroup
	wg.Add(4)

	for i := 0; i < 4; i++ {
		go sortPartition(partitions[i], i+1, &wg)
	}

	wg.Wait()

	fmt.Println()
	merged := merge(partitions[0], partitions[1])
	merged = merge(merged, partitions[2])
	merged = merge(merged, partitions[3])

	fmt.Println("Fully sorted array:", merged)
}
