# Build stage
FROM golang:1.25.5-alpine AS builder

WORKDIR /app

# Copy go mod files
COPY go.mod go.sum ./

# Download dependencies
RUN go mod download

# Copy the source code
COPY . .

# Build the application
RUN CGO_ENABLED=0 GOOS=linux go build -o web-scraper .

# Final stage
FROM alpine:latest

WORKDIR /root/

# Copy the binary from builder
COPY --from=builder /app/web-scraper .

# Copy static files if needed
COPY --from=builder /app/static ./static

# Expose port (default port, can be overridden at runtime)
EXPOSE 8080

# Run the application
CMD ["./web-scraper"]
