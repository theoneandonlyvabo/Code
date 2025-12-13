package main

import (
	"bufio"
	"fmt"
	"os"
	"sort"
	"strconv"
	"strings"
)

func main() {
	slice := make([]int, 0, 3)

	scanner := bufio.NewScanner(os.Stdin)

	for {
		fmt.Print("Enter Integer (or 'X' to exit program): ")

		scanner.Scan()
		input := strings.TrimSpace(scanner.Text())

		if strings.ToUpper(input) == "X" {
			fmt.Println("Exitting Program, See you later!")
			break
		}

		num, err := strconv.Atoi(input)
		if err != nil {
			fmt.Println("Invalid, Please enter correct integer or exit.")
			continue
		}

		slice = append(slice, num)

		sort.Ints(slice)

		fmt.Println("Sorted Slice:", slice)
	}
}
