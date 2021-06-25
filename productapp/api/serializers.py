from api.models import Product
from django.db import models
from rest_framework import fields, serializers

class ProductSerializer(serializers.ModelSerializer):
    class Meta:
        model=Product
        fields='__all__'
