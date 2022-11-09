include(":maps")
if (System.getenv("CICD") == "" || System.getenv("CICD") == null) {
    include(":demo")
}