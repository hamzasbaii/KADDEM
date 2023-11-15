# Stage 1: Build Angular App
FROM node:18.13 AS builder
WORKDIR /app

# Copy package.json and install Angular CLI
COPY package*.json ./
RUN npm install -g @angular/cli

# Install app dependencies and build
RUN npm install
COPY . .
RUN ng build --configuration=production

# Stage 2: Create Nginx Container
FROM nginx:alpine

# Copy the built app from the builder stage
COPY --from=builder /app/dist/* /usr/share/nginx/html/

# Expose port and run Nginx
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
