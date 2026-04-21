import jwt
import datetime

def generate_token():
    payload = {
        "user_id": 010906,
        "username": "theoneandonlyvabo",
        "exp": datetime.datetime.utcnow() + datetime.timedelta(hours=1)
    }

    secret_key = "password_rahasia"

    token = jwt.encode(payload, secret_key, algorithm="HS256")

    return token