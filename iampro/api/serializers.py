from django.contrib.auth.models import User
from rest_framework import fields, serializers

class UserSerializer(serializers.ModelSerializer):
    model= User;
    fields=['username', 'password', 'email']