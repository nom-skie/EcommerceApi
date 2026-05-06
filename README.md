# EcommerceApi

A full-stack e-commerce application built with **Spring Boot** (backend) 
and **HTML/CSS/JavaScript** (frontend), featuring persistent database 
integration via Spring Data JPA and dynamic product loading using the Fetch API.

## Authors
- Jayson Alastoy
- Mila Jane Españo

## Tech Stack
- Java 21
- Spring Boot 4.0.5
- Spring Data JPA + Hibernate
- MySQL
- Gradle
- Lombok
- Spring Web

---

## Database Schema

The application uses a **MySQL** database named `ecommerce_db` with two tables.

### Tables and Relationships

#### **1. Categories Table**
Stores the classifications for products.

| Column | Type | Constraints |
| :--- | :--- | :--- |
| **id** | `BIGINT` | PRIMARY KEY, AUTO_INCREMENT |
| **name** | `VARCHAR(100)` | NOT NULL, UNIQUE |

#### **2. Products Table**
Stores detailed information for individual items.

| Column | Type | Constraints |
| :--- |--- | :--- |
| **id** | `BIGINT` | PRIMARY KEY, AUTO_INCREMENT |
| **name** | `VARCHAR(255)` | NOT NULL |
| **description** | `TEXT` | |
| **price** | `DECIMAL(10, 2)` | NOT NULL |
| **stock_quantity** | `INT` | DEFAULT 0 |
| **image_url** | `VARCHAR(512)` | |
| **category_id** | `BIGINT` | FOREIGN KEY → `categories(id)` |

### Relationship
- **One-to-Many**: One `Category` has many `Products`
- A `product` belongs to one `category` via `category_id` (foreign key)
- Implemented using `@OneToMany` and `@ManyToOne` JPA annotations with `CascadeType.ALL` and `FetchType.LAZY`
### Database Screenshot
![Database Schema](API%20Testing%20(screenshots)/database%20table.png)
 
---

## API Endpoints

All endpoints are prefixed with `/api/v1`.

### Categories

| Method | Endpoint           | Description             | Status Codes |
|--------|--------------------|-------------------------|--------------|
| GET    | `/categories`      | Get all categories      | 200          |
| GET    | `/categories/{id}` | Get category by ID      | 200, 404     |
| POST   | `/categories`      | Create a new category   | 201, 400     |
| DELETE | `/categories/{id}` | Delete a category by ID | 204, 404     |

### Products

| Method | Endpoint               | Description                        | Status Codes |
|--------|------------------------|------------------------------------|--------------|
| GET    | `/products`            | Get all products                   | 200          |
| GET    | `/products/{id}`       | Get product by ID                  | 200, 404     |
| GET    | `/products/filter`     | Filter by name, category, or price | 200, 400     |
| POST   | `/products`            | Create a new product               | 201, 400     |
| PUT    | `/products/{id}`       | Replace a product entirely         | 200, 404     |
| PATCH  | `/products/{id}`       | Partially update a product         | 200, 404     |
| DELETE | `/products/{id}`       | Delete a product by ID             | 204, 404     |

### Filter Examples
```
GET http://localhost:8080/api/v1/products/filter?filterType=name&filterValue=airpods
GET http://localhost:8080/api/v1/products/filter?filterType=category&filterValue=Electronics
GET http://localhost:8080/api/v1/products/filter?filterType=price&filterValue=1000
```

### Sample Request Body — Create Product
```json
{
  "name": "Apple Airpods Pro 2",
  "description": "Pro-level Active Noise Cancellation earbuds",
  "price": 12490.00,
  "stockQuantity": 15,
  "category": { "id": 1 },
  "imageUrl": "images/Apple AirPods Pro 2.jpg"
}
```

### Error Response Format
All errors return this format:
```json
{
  "timestamp": "2026-04-27T10:00:00",
  "errorCode": 400,
  "message": "Product name is required"
}
```
 
---

## Frontend – Fetch API Integration

The frontend dynamically loads products from the backend using the Fetch API with `async/await`.

### Key Features
- `fetchProducts()` fetches all products on page load
- Manual `response.ok` check handles 404 and 500 errors
- `try/catch` block handles network failures
- Empty state message displayed if no products are returned
- Product cards are dynamically injected into the `<main>` tag
### Browser Console Screenshot
![Fetch Success](API%20Testing%20(screenshots)/browse%20console.png)
---

## CORS Configuration

A `WebConfig` class implementing `WebMvcConfigurer` was added to allow the frontend
(running on `http://localhost:5500` or `http://127.0.0.1:5500`) to communicate with
the backend on port `8080`.
 
---

## How to Run

1. Clone the repository
```bash
git clone https://github.com/nom-skie/EcommerceApi
```
2. Open in IntelliJ IDEA
3. Run `EcommerceApiApplication.java` or use:
```bash
./gradlew bootRun
```
4. API will be available at `http://localhost:8080`

## API Endpoints

### Get All Products
- **Method:** GET
- **URL:** `http://localhost:8080/api/v1/products`
- **Response:** `200 OK`

### Get Product by ID
- **Method:** GET
- **URL:** `http://localhost:8080/api/v1/products/{id}`
- **Response:** `200 OK` or `404 Not Found`

### Filter Products
- **Method:** GET
- **URL:** `http://localhost:8080/api/v1/products/filter?filterType={type}&filterValue={value}`
- **Filter Types:** `name`, `category`, `price`
- **Response:** `200 OK` or `400 Bad Request`
- **Examples:**
    - `http://localhost:8080/api/v1/products/filter?filterType=category&filterValue=Electronics`
    - `http://localhost:8080/api/v1/products/filter?filterType=name&filterValue=apple`
    - `http://localhost:8080/api/v1/products/filter?filterType=price&filterValue=1000`

### Create Product
- **Method:** POST
- **URL:** `http://localhost:8080/api/v1/products`
- **Response:** `201 Created` or `400 Bad Request`
- **Sample Request Body:**
```json
{
  "name": "New Product",
  "description": "Product description",
  "price": 999.00,
  "category": "Electronics",
  "stockQuantity": 10,
  "imageUrl": "images/product.jpg"
}
```

### Update Product (Full)
- **Method:** PUT
- **URL:** `http://localhost:8080/api/v1/products/{id}`
- **Response:** `200 OK` or `404 Not Found`
- **Sample Request Body:**
```json
{
  "name": "Updated Product",
  "description": "Updated description",
  "price": 1099.00,
  "category": "Electronics",
  "stockQuantity": 5,
  "imageUrl": "images/product.jpg"
}
```

### Partial Update Product
- **Method:** PATCH
- **URL:** `http://localhost:8080/api/v1/products/{id}`
- **Response:** `200 OK` or `404 Not Found`
- **Sample Request Body:**
```json
{
  "price": 899.00
}
```

### Delete Product
- **Method:** DELETE
- **URL:** `http://localhost:8080/api/v1/products/{id}`
- **Response:** `204 No Content` or `404 Not Found`

## Error Responses
All errors return this format:
```json
{
  "timestamp": "2026-04-27T10:00:00",
  "errorCode": 400,
  "message": "Product name is required"
}
```

## Known Limitations
- Data is stored in-memory and resets every time the app restarts
- No database integration
- No authentication or authorization

## Pair Programming Sessions

| Session | Driver | Navigator      |
|---------|---|----------------|
| Task 1  | Jayson Alastoy | Mila Jane Españo |
| Task 2  | Mila Jane Españo | Jayson Alastoy |
| Task 3  | Jayson Alastoy | Mila Jane Españo |
| Task 4  | Jayson Alastoy | Mila Jane Españo |
| Task 4  | Mila Jane Españo | Jayson Alastoy |
| Task 5  | Jayson Alastoy | Mila Jane Españo |
| Task 5  | Mila Jane Españo| Jayson Alastoy |
| Task 6  | Jayson Alastoy | Mila Jane Españo |
| Task 7  | Mila Jane Españo| Jayson Alastoy |
| Task 7  | Jayson Alastoy | Mila Jane Españo |

## Screenshots
All API testing screenshots are in the `API Testing (screenshots)/` folder.

### Test Results
| Test | Status Code | Screenshot  |
|---|---|-------------|
| GET all products | 200 OK | ![GET All](API%20Testing%20(screenshots)/get-all(1).png) |
| GET single product | 200 OK | ![GET Single](API%20Testing%20(screenshots)/get-by-id(1).png) |
| GET single product | 200 OK | ![GET Single](API%20Testing%20(screenshots)/get-by-id(2).png) |
| GET single product | 200 OK | ![GET Single](API%20Testing%20(screenshots)/get-by-id(3).png) |
| POST create product | 201 Created | ![POST](API%20Testing%20(screenshots)/post-create(1).png) |
| POST create product | 201 Created | ![POST](API%20Testing%20(screenshots)/post-create(2).png) 
| POST create product | 201 Created | ![POST](API%20Testing%20(screenshots)/post-create(3).png) 
| PUT update product | 200 OK | ![PUT](API%20Testing%20(screenshots)/put-update.png) |
| PATCH partial update | 200 OK | ![PATCH](API%20Testing%20(screenshots)/patch-update.png) |
| DELETE product | 204 No Content | ![DELETE](API%20Testing%20(screenshots)/delete.png) |
| GET all products | 200 OK | ![GET All](API%20Testing%20(screenshots)/get-all(2).png) |
| Filter products | 200 OK | ![FILTER](API%20Testing%20(screenshots)/filter.png) |
| Error - 404 Not Found | 404 Not Found | ![404](API%20Testing%20(screenshots)/error-404.png) |
| Error - 400 Bad Request | 400 Bad Request  | ![404](API%20Testing%20(screenshots)/error-400(2).png) |
| Error - 400 Bad Request | 400 Bad Request  | ![404](API%20Testing%20(screenshots)/error-400(3).png) |
| Error - 400 Bad Request | 400 Bad Request | ![404](API%20Testing%20(screenshots)/error-400(4).png) |
| Error - 400 Bad Request | 400 Bad Request | ![400](API%20Testing%20(screenshots)/error-400(1).png) |
| Error - 500 Server Error | 500 Internal Server Error | ![500](API%20Testing%20(screenshots)/error-500.png) |