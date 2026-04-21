import jwt                  
import base64
import json
from cryptography.hazmat.primitives import serialization
from cryptography.hazmat.primitives.asymmetric import rsa
from cryptography.hazmat.backends import default_backend

print("=" * 60)
print("   JWT SECURITY LAB")
print("=" * 60)


# Membuat JWT dengan algoritma HS256

# HS256 menggunakan satu secret key yang sama untuk sign & verify
# Cocok untuk sistem internal / single server
HS256_SECRET = "super-secret-key-jangan-dibagikan"

# Payload: data yang ingin disimpan di dalam token
payload_hs256 = {
    "sub": "user_196",          # subject (siapa pemilik token)
    "name": "Airel Adrivano",    # nama pengguna
    "role": "Student",
    "iat": 1700000000,          # issued at (waktu dibuat, epoch)
    "exp": 9999999999           # expiration (waktu expired, epoch)
}

# Buat token HS256
token_hs256 = jwt.encode(payload_hs256, HS256_SECRET, algorithm="HS256")
print(f"Token HS256:\n{token_hs256}\n")

# Verifikasi token HS256
decoded_hs256 = jwt.decode(token_hs256, HS256_SECRET, algorithms=["HS256"])
print(f"Decoded HS256 payload:\n{json.dumps(decoded_hs256, indent=2)}\n")


# Membuat JWT dengan algoritma RS256

# RS256 menggunakan keypair RSA:
#   - Private key  → digunakan SERVER untuk MENANDATANGANI token
#   - Public key   → bisa dibagikan ke siapa saja untuk MEMVERIFIKASI

# Generate RSA keypair (dalam praktik nyata, ini disimpan di file .pem)
private_key = rsa.generate_private_key(
    public_exponent=65537,
    key_size=2048,
    backend=default_backend()
)
public_key = private_key.public_key()

# Konversi ke format PEM (format standar kriptografi)
private_pem = private_key.private_bytes(
    encoding=serialization.Encoding.PEM,
    format=serialization.PrivateFormat.TraditionalOpenSSL,
    encryption_algorithm=serialization.NoEncryption()
)
public_pem = public_key.public_bytes(
    encoding=serialization.Encoding.PEM,
    format=serialization.PublicFormat.SubjectPublicKeyInfo
)

print("Private Key (simpan rahasia di server!):")
print(private_pem.decode()[:100] + "...\n")

# Payload untuk RS256
payload_rs256 = {
    "sub": "user_196",
    "name": "Airel Adrivano",
    "role": "Student",
    "iss": "https://myapp.com",   # issuer: siapa yang menerbitkan token
    "aud": "myapp-client",        # audience: untuk siapa token ini
    "exp": 9999999999
}

# Sign dengan private key
token_rs256 = jwt.encode(payload_rs256, private_pem, algorithm="RS256")
print(f"Token RS256:\n{token_rs256}\n")

# Verifikasi dengan public key (bisa dilakukan siapa saja yang punya public key)
decoded_rs256 = jwt.decode(
    token_rs256,
    public_pem,
    algorithms=["RS256"],
    audience="myapp-client"
)
print(f"Decoded RS256 payload:\n{json.dumps(decoded_rs256, indent=2)}\n")


# Eksperimen manipulasi JWT (tampering attack)
print("-" * 60)
print("\n[BAGIAN 3] Eksperimen Manipulasi JWT (Tampering)\n")

# Ambil token HS256 yang valid dan coba ubah payload-nya tanpa secret
parts = token_hs256.split(".")
header_b64  = parts[0]
payload_b64 = parts[1]
signature   = parts[2]   # signature asli

# Decode payload (base64url → JSON)
# Tambahkan padding '=' karena base64url tidak selalu punya padding
padding = 4 - len(payload_b64) % 4
payload_decoded = base64.urlsafe_b64decode(payload_b64 + "=" * padding)
payload_dict = json.loads(payload_decoded)

print(f"Payload ASLI: {payload_dict}")

# MANIPULASI: ubah role dari 'student' menjadi 'admin'
payload_dict["role"] = "admin"   # ← penyerang mencoba eskalasi privilege!
print(f"Payload DIMANIPULASI: {payload_dict}")

# Encode kembali ke base64url (tanpa secret, tentu saja)
manipulated_payload = base64.urlsafe_b64encode(
    json.dumps(payload_dict, separators=(',', ':')).encode()
).rstrip(b'=').decode()

# Gabungkan dengan signature LAMA (signature tidak berubah!)
tampered_token = f"{header_b64}.{manipulated_payload}.{signature}"
print(f"\nToken yang dimanipulasi:\n{tampered_token}\n")

# Coba verifikasi token yang sudah dimanipulasi
print("Mencoba verifikasi token yang dimanipulasi...")
try:
    jwt.decode(tampered_token, HS256_SECRET, algorithms=["HS256"])
    print("✓ Verifikasi BERHASIL (ini mestinya TIDAK terjadi!)")
except jwt.exceptions.InvalidSignatureError as e:
    print(f"✗ Verifikasi GAGAL (sesuai harapan!): {e}")
    print("  → Signature tidak cocok karena payload berubah tapi key tidak diketahui penyerang.")


# Decode & Analisis Struktur JWT

# JWT terdiri dari 3 bagian yang dipisah titik (.):
#   [HEADER].[PAYLOAD].[SIGNATURE]
# Semua bagian di-encode dengan Base64URL

sample_token = token_hs256
parts = sample_token.split(".")

print(f"Token penuh:\n{sample_token}\n")
print(f"Jumlah bagian: {len(parts)} (dipisah oleh titik '.')\n")

# Decode Header
def decode_b64(s):
    padding = 4 - len(s) % 4
    return json.loads(base64.urlsafe_b64decode(s + "=" * padding))

header  = decode_b64(parts[0])
payload = decode_b64(parts[1])
sig_raw = parts[2]

print(f"PART 1 - HEADER (raw base64url): {parts[0]}")
print(f"PART 1 - HEADER (decoded):       {json.dumps(header, indent=2)}")
print(f"  → 'alg': algoritma signing ({header.get('alg')})")
print(f"  → 'typ': tipe token ({header.get('typ')})\n")

print(f"PART 2 - PAYLOAD (raw base64url): {parts[1]}")
print(f"PART 2 - PAYLOAD (decoded):       {json.dumps(payload, indent=2)}")
print(f"  → 'sub': subject / pemilik token")
print(f"  → 'exp': expiry time (unix timestamp)\n")

print(f"PART 3 - SIGNATURE (raw): {sig_raw[:40]}...")
print(f"  → Dihasilkan dari HMAC-SHA256(header + '.' + payload, secret)")
print(f"  → Tidak bisa di-decode, hanya bisa diverifikasi dengan secret\n")


# Demonstrasi kerentanan algoritma 'none'
print("-" * 60)
print("\n[BAGIAN 5] Demonstrasi Kerentanan Algoritma 'none'\n")

# Serangan "none algorithm": penyerang mengubah header alg menjadi 'none'
# sehingga server (yang tidak aman) tidak melakukan verifikasi signature

malicious_header = {"alg": "none", "typ": "JWT"}
malicious_payload = {"sub": "user_196", "role": "admin", "exp": 9999999999}

h = base64.urlsafe_b64encode(json.dumps(malicious_header, separators=(',',':')).encode()).rstrip(b'=').decode()
p = base64.urlsafe_b64encode(json.dumps(malicious_payload, separators=(',',':')).encode()).rstrip(b'=').decode()

none_token = f"{h}.{p}."   # signature dikosongkan!
print(f"Token 'none' algorithm (berbahaya!):\n{none_token}\n")

# PyJWT secara default MENOLAK algoritma 'none' - ini perilaku yang benar
try:
    jwt.decode(none_token, "", algorithms=["none"])
    print("✓ Token diterima (BERBAHAYA! Library ini rentan!)")
except Exception as e:
    print(f"✗ PyJWT menolak algoritma 'none': {type(e).__name__}")
    print("  → PyJWT aman: selalu tolak 'none' di production!\n")

print("\n" + "=" * 60)
print("   SELESAI - Semua eksperimen JWT berhasil dijalankan")
print("=" * 60)