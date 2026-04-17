from flask import Flask, request, jsonify
import tiktoken

app = Flask(__name__)

# Use GPT tokenizer
enc = tiktoken.encoding_for_model("gpt-3.5-turbo")

@app.route("/tokens", methods=["POST"])
def count_tokens():
    data = request.json
    text = data.get("text", "")

    tokens = len(enc.encode(text))

    return jsonify({
        "tokens": tokens
    })

if __name__ == "__main__":
    app.run(port=5001)
