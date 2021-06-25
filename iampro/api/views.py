from api.serializers import UserSerializer
from rest_framework.views import APIView
from django.contrib.auth.models import User 
from rest_framework.response import Response
# Create your views here.
class RegView(APIView):
    
    def post(self,request):
        pass

class LoginView(APIView):
    
    def post(self,request):
        pass

class LogoutView(APIView):
    
    def get(self,request):
        pass

