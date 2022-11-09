include(":demo")
if (System.getenv("CICD") == "" || System.getenv("CICD") == null) {
    include(":maps")
}