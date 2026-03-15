**API Endpoints**
- **Base path:** / (application root)

**Customers**
- **GET /customers/get/{id}:** Returns a `Customers` JSON by path `id`. Path variable: `id` (int). Responses: 200 with customer, 404 if not found.
- **PUT /customers/put/{id}:** Updates customer + address. Path variable: `id` (int). Request body: `CustomerDto` JSON. Responses: 200 message on success, 404 if customer not found, 500 on failure.
- **POST /customers/post:** Creates a new customer. Request body: `Customers` JSON (entity). Responses: 200 on success, 400 if ID conflict.
- **DELETE /customers/delete/{id}:** Deletes a customer. Path variable: `id` (int). Optional query param: `idParam` (Integer) — if supplied it overrides the path id. Responses: 200 on success, 404 if not found.

**Orders**
- **GET /orders/get/{id}:** Returns an `Orders` JSON by path `id`. Path variable: `id` (int). Responses: 200 with order, 404 if not found.
- **PUT /orders/put/{id}:** Placeholder update by id. Path variable: `id` (int). Optional query param: `idParam` (Integer) — overrides the path id when provided. Responses: 200 message if exists, 404 if not found.
- **POST /orders/post:** Creates an order. Request body: `Orders` JSON (entity). Responses: 200 on success.
- **DELETE /orders/delete/{id}:** Deletes an order. Path variable: `id` (int). Optional query param: `idParam` (Integer) — overrides the path id. Responses: 200 on success, 404 if not found.

**Products**
- **GET /products/get/{id}:** Returns a `Products` JSON by id. Path variable: `id` (int). Responses: 200 with product, 404 if not found.
- **PUT /products/update/{id}:** Updates product by id. Path variable: `id` (int). Request body: `Products` JSON (entity). Responses: 200 with updated product, 404 if not found.
- **POST /products/create:** Creates a product. Request body: `Products` JSON (entity). Responses: 200 with saved product.
- **DELETE /products/delete/{id}:** Deletes a product. Path variable: `id` (int). Responses: 200 on success, 404 if not found.
- **POST /products/bulk-modify-price/{percentage}:** Adjusts prices by percentage. Path variable: `percentage` (double). Responses: 200 with count of updated products.

**Suppliers**
- **GET /suppliers/get/{id}:** Returns a `Supplier` JSON by id. Path variable: `id` (int). Responses: 200 with supplier, 404 if not found.
- **PUT /suppliers/put/{id}:** Updates supplier by id. Path variable: `id` (int). Request body: `Supplier` JSON (entity). Responses: 200 message on success, 404 if not found.
- **POST /suppliers/post:** Creates a supplier. Request body: `Supplier` JSON (entity). Responses: 200 on success.
- **DELETE /suppliers/delete/{id}:** Deletes a supplier. Path variable: `id` (int, optionally omitted). Optional query param: `idParam` (Integer) — if provided it overrides the path id. Responses: 200 on success, 404 if not found.

**Notes / Requirements**
- **Content-Type:** JSON for all endpoints that accept a request body.
- **IDs:** Most endpoints use integer IDs. `percentage` is a double for bulk price changes.
- **Transactionality:** `DELETE /customers/delete/{id}` is annotated `@Transactional`.
- **DTOs / Entities:** Request bodies use project entity classes (`Customers`, `Orders`, `Products`, `Supplier`) or `CustomerDto` for customer update. See source in `demo/src/main/java/fi/metropolia/ilkkas/demo/controller/` for implementation details.
