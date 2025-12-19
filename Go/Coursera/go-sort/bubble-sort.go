package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

func Swap(slice []int, i int) {
	slice[i], slice[i+1] = slice[i+1], slice[i]
}

func BubbleSort(slice []int) {
	n := len(slice)
	for i := 0; i < n-1; i++ {
		for j := 0; j < n-i-1; j++ {
			if slice[j] > slice[j+1] {
				Swap(slice, j)
			}
		}
	}
}

func main() {
	fmt.Print("Enter up to 10 integers separated by spaces: ")
	
	scanner := bufio.NewScanner(os.Stdin)
	scanner.Scan()
	input := scanner.Text()
	
	parts := strings.Fields(input)
	var numbers []int
	
	for i, part := range parts {
		if i >= 10 {
			break
		}
		num, err := strconv.Atoi(part)
		if err != nil {
			fmt.Println("Invalid input:", part)
			return
		}
		numbers = append(numbers, num)
	}
	
	BubbleSort(numbers)
	
	for i, num := range numbers {
		if i > 0 {
			fmt.Print(" ")
		}
		fmt.Print(num)
	}
	fmt.Println()
}