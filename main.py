import subprocess

def run_ast_printer(expression):
    # Build the command string
    command = ["java", "-cp", "bin", "com.spy.ast.ASTBuilder"]

    # Capture the output using PIPE
    process = subprocess.Popen(command, stdin=subprocess.PIPE, stdout=subprocess.PIPE, stderr=subprocess.STDOUT)

    # Get the output
    output, _ = process.communicate(input=expression.encode())

    # Decode the output and return
    return output.decode().strip()

# Example usage
expression = "2 + 2 * 2"
ast_output = run_ast_printer(expression)
print(ast_output)

