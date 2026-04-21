from django.http import JsonResponse  
from .utils import generate_token

def create_token(request):
    token = generate_token()
    return JsonResponse({"token": token})

