# Basic and Advanced Calculator with Admin Audit Features

A comprehensive web-based calculator application that supports both basic and advanced mathematical operations with built-in administrative auditing capabilities.

## Overview

This project implements a feature-rich calculator web application that combines everyday calculation needs with powerful administrative oversight. It provides both basic arithmetic operations and advanced mathematical functions while maintaining detailed usage logs for administrative review.

## login detail
- Username: Test
- Password: Test

## To Use The System
- login as admin with the give usermane and password
- create a user
- give the user a role
- the user will be able to do calculation if given a client role
- admin will be able to view all calculations 

## Features

### User Functionality

#### Basic Calculator
- Simple arithmetic operations (addition, subtraction, multiplication, division)
- User-friendly interface with input box and result display
- Real-time calculation updates

#### Advanced Calculator
- Logarithmic calculations
- Exponentiation and square root
- Factorials and percentages
- Easy toggle between basic and advanced modes

#### User Session Management
- Session-based calculation history
- Optional user registration for preference saving
- Seamless calculation tracking

### Admin Panel

#### Comprehensive Audit Logs
- Detailed tracking of all calculations
- Time-stamped entries
- Operation type classification
- Input and result logging
- User attribution (when available)

#### User Management Features
- Complete user activity overview
- Usage statistics and metrics
- Calculation type distribution analysis
- User behavior tracking

#### Reporting Capabilities
- Export functionality for logs
- Custom report generation

## Technology Stack

### Frontend
- JSF (JavaServer Faces)
- PrimeFaces for UI components
- Bootstrap integration for responsive design

### Backend
- Java EE / Jakarta EE
- WildFly application server
- JPA/Hibernate for persistence
- MySQL database

### Deployment
- Amazon EC2 for application hosting
- Amazon RDS for database management

## Security Features

- Robust input validation
- Secure authentication system
- Rate limiting implementation
- Encrypted audit logging
- Role-based access control

## Use Cases

- Educational environments
- Professional workspaces
- Administrative oversight
- Usage pattern analysis
- Productivity tracking

## Installation

1. Clone the repository
```bash
git clone https://github.com/AmogelangPhangisa/moCalculator

2. mvn clean install
3. mvn wildfly:deploy
