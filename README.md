# 🧠 Spring Boot MCP Server

This project is a **Spring Boot application** that functions as a **Model Context Protocol (MCP) Server**.  
The **MCP** is an open, standardized protocol that allows **LLMs (Large Language Models)** to connect with external data sources and tools — enabling integration between AI platforms (like Claude Desktop) and backend systems.

This server uses a **MySQL database (running via Docker Compose)** to manage a simple **product inventory**, and exposes backend functionalities as **MCP tools** that an LLM can invoke directly.

---

## 🏗️ Architecture Overview

The system follows a **client-server** architecture:

| Component | Description |
|------------|-------------|
| **MCP Host (Client)** | An application such as **Claude Desktop**, which handles tool invocation. |
| **LLM (Language Model)** | The reasoning engine (e.g., **Claude 4.5**) that decides which tools to call. |
| **MCP Server (This App)** | A Spring Boot application packaged as an executable JAR. It communicates with the host over **STDIO**. |

**Communication:**  
The MCP Host and the MCP Server interact via **Standard Input/Output (STDIO)**.

**Goal:**  
Allow a user to ask natural language questions (e.g., *“List all electronics products under $100”*) — the LLM then chooses the correct tool on this server to query or modify the inventory database.

---

## ⚙️ Technologies & Dependencies

| Dependency | Purpose |
|-------------|----------|
| **Spring Boot 3.5.6** | Core application framework |
| **Model Context Protocol Server** | Enables full MCP server capabilities |
| **Spring Data JPA** | Object-relational mapping and repository support |
| **MySQL** | Persistent relational database (via Docker Compose) |
| **Lombok** | Auto-generates getters, setters, constructors |

**Build Tool:** Maven  
**Language:** Java 17+  

---


