package main

import (
	"bufio"
	"fmt"
	"os"
	"strings"
)

type Animal interface {
	Eat()
	Move()
	Speak()
}

type Cow struct {
	name string
}

func (c Cow) Eat() {
	fmt.Println("grass")
}

func (c Cow) Move() {
	fmt.Println("walk")
}

func (c Cow) Speak() {
	fmt.Println("moo")
}

type Bird struct {
	name string
}

func (b Bird) Eat() {
	fmt.Println("worms")
}

func (b Bird) Move() {
	fmt.Println("fly")
}

func (b Bird) Speak() {
	fmt.Println("peep")
}

type Snake struct {
	name string
}

func (s Snake) Eat() {
	fmt.Println("mice")
}

func (s Snake) Move() {
	fmt.Println("slither")
}

func (s Snake) Speak() {
	fmt.Println("hsss")
}

func main() {
	animals := make(map[string]Animal)
	scanner := bufio.NewScanner(os.Stdin)

	for {
		fmt.Print("> ")
		scanner.Scan()
		input := scanner.Text()

		parts := strings.Fields(input)
		if len(parts) != 3 {
			fmt.Println("Invalid input. Please enter command name type/info.")
			continue
		}

		command := strings.ToLower(parts[0])
		name := parts[1]
		param := strings.ToLower(parts[2])

		if command == "newanimal" {
			switch param {
			case "cow":
				animals[name] = Cow{name: name}
				fmt.Println("Created it!")
			case "bird":
				animals[name] = Bird{name: name}
				fmt.Println("Created it!")
			case "snake":
				animals[name] = Snake{name: name}
				fmt.Println("Created it!")
			default:
				fmt.Println("Unknown animal type. Use cow, bird, or snake.")
			}
		} else if command == "query" {
			animal, exists := animals[name]
			if !exists {
				fmt.Println("Animal not found.")
				continue
			}

			switch param {
			case "eat":
				animal.Eat()
			case "move":
				animal.Move()
			case "speak":
				animal.Speak()
			default:
				fmt.Println("Unknown action. Use eat, move, or speak.")
			}
		} else {
			fmt.Println("Unknown command. Use newanimal or query.")
		}
	}
}
