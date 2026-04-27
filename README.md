# EcommerceApi

A RESTful API backend for an e-commerce product catalog built with Spring Boot.
All data is stored in-memory using a `List<Product>`.

## Authors
- Jayson Alastoy
- Mila Jane Españo

## Tech Stack
- Java 21
- Spring Boot 4.0.5
- Gradle
- Lombok
- Spring Web

## How to Run

1. Clone the repository
```bash
git clone <your-repo-url>
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