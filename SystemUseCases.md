# PX Manager System Use Cases #

This page contains the Use Cases of this system: PX Manager


## What is a Use Case? ##

A Use Case represents a discrete unit of interaction between a user(human/machine) and the system.
This interaction is a single unit of meaningful work, such as Create Acount or View Account Details.

#### User Login ####
| **User** | **System** |
|:---------|:-----------|
|Enters userName/Password|            |
|          |Looks up in the DB a userName |
|          |If userName Exists in DB it will match the entered password's hash with the hash that we have in DB for that userName|
|          | If Credentials are OK |
|          |User is welcomed into the System. User Welcome screen is displayed. Once the user logs in, the system must keep track of it's active session |
|          | If user does not exist, or password does not match it will say "Incorrect UserName or password", "sign-up for an account", "forgot my password"|