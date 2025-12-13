package main

import (
	"bufio"
	"fmt"
	"os"
	"strings"
)

type name struct {
	firstName string
	lastName  string
}

func main() {
	var fileName string
	var names []name

	fmt.Print("Enter the name of the text file: ")
	fmt.Scan(&fileName)

	file, err := os.Open(fileName)
	if err != nil {
		fmt.Println("Error opening file: ", err)
		return
	}

	defer file.Close()

	scanner := bufio.NewScanner(file)
	for scanner.Scan() {
		line := scanner.Text()
		parts := strings.Fields(line)

		if len(parts) >= 2 {
			n := name{
				firstName: parts[0],
				lastName:  parts[1],
			}

			names = append(names, n)
		}
	}

	if err := scanner.Err(); err != nil {
		fmt.Println("Error reading file: ", err)
		return
	}

	for _, n := range names {
		fmt.Printf("%s %s\n", n.firstName, n.lastName)
	}
}
