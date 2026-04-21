from Crypto.Cipher import AES
from Crypto.Random import get_random_bytes

def encrypt_data(data):
    key = get_random_bytes(16)  # AES-128

    cipher = AES.new(key, AES.MODE_EAX)

    ciphertext, tag = cipher.encrypt_and_digest(data.encode())

    return {
        "key": key.hex(),
        "nonce": cipher.nonce.hex(),
        "tag": tag.hex(),
        "ciphertext": ciphertext.hex()
    }

