from django.http import JsonResponse
from .utils import encrypt_data

def encrypt_view(request):
    result = encrypt_data("Hello, World!")

    return JsonResponse(result)

