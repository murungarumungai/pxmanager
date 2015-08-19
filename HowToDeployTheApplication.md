# How to deploy the application #
The application runs in **Glasfish Application Server** v2.1.1

# Glassfish Resorces #
  1. Install the DB:expensemanager, run the SQL Script:expensemanager.sql
  1. JDBC Resorce is based on a connection pool. Create a **Connection Pool** which connects to the DB
  1. JDBC Resorce: jdbc/expensemanager