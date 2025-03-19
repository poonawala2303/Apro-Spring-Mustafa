## Project Structure

Bank-Application-Backend/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── aurionpro/
│                   └── banking/
│                       ├── Application.java
│                       ├── controller/
│                       │   ├── AccountController.java
│                       │   └── UserController.java
│                       ├── dto/
│                       │   ├── AccountRequestDto.java
│                       │   ├── AccountResponseDto.java
│                       │   ├── PageResponse.java
│                       │   ├── TransactionRequestDto.java
│                       │   ├── TransactionResponseDto.java
│                       │   ├── UserRequestDto.java
│                       │   └── UserResponseDto.java
│                       ├── entity/
│                       │   ├── Account.java
│                       │   ├── Role.java
│                       │   ├── Transaction.java
│                       │   └── User.java
│                       ├── error/
│                       │   └── AccountErrorResponse.java
│                       ├── exception/
│                       │   ├── AccountNotFoundException.java
│                       │   ├── GlobalException.java
│                       │   ├── TransactionApiException.java
│                       │   ├── UserApiException.java
│                       │   └── UserNotFoundException.java
│                       ├── repository/
│                       │   ├── AccountRepository.java
│                       │   ├── TransactionRepository.java
│                       │   └── UserRepository.java
│                       └── service/
│                           ├── AccountService.java
│                           ├── AccountServiceImpl.java
│                           ├── UserService.java
│                           └── UserServiceImpl.java
└── pom.xml (or build.gradle)