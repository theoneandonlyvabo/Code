package main

import (
	"fmt"
	"strings"
)

func main() {
	var input string

	fmt.Printf("Input string: ")
	fmt.Scan(&input)

	nocase := strings.ToLower(input)

	if strings.HasPrefix(nocase, "i") && strings.HasSuffix(nocase, "n") && strings.Contains(nocase, "a") {
		fmt.Println("Found!")
		return
	}

	fmt.Println("Not Found!")
}
