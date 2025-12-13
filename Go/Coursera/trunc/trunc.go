package main

import "fmt"

func main() {
	var num float64

	fmt.Print("Enter floating point number: ")
	fmt.Scan(&num)

	truncated := int(num)

	fmt.Printf("Truncated integer: %d\n", truncated)
}
