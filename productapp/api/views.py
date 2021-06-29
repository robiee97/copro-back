from .producer import produceData
from rest_framework.views import APIView
from api.serializers import ProductSerializer
from api.models import Product, User
from rest_framework import viewsets
from rest_framework.response import Response
from rest_framework import status
import random
# Create your views here.

class ProductViewSet(viewsets.ViewSet):
    def list(self,request):
        products= Product.objects.all()
        serializer=ProductSerializer(products,many=True)
        return Response(serializer.data, status=status.HTTP_200_OK)
        
    def create(self,request):
        serializer=ProductSerializer(data=request.data)
        serializer.is_valid(raise_exception=True)
        serializer.save()
        #producer
        produceData('product_created', serializer.data)
        return Response(serializer.data, status=status.HTTP_201_CREATED)
        
    def retrieve(self,request,pk=None):
        product= Product.objects.get(id=pk)
        serializer=ProductSerializer(product)
        return Response(serializer.data, status=status.HTTP_302_FOUND)
    
    def update(self,request,pk=None):
        product= Product.objects.get(id=pk)
        serializer=ProductSerializer(data=request.data, instance=product)
        serializer.is_valid(raise_exception=True)
        serializer.save()
        #producer
        produceData('product_updated',serializer.data)
        return Response(serializer.data, status=status.HTTP_202_ACCEPTED)

    def destroy(self,request,pk=None):
        product= Product.objects.get(id=pk)
        product.delete()
        #producer
        produceData('product_deleted',pk)
        return Response(status=status.HTTP_204_NO_CONTENT)


class UserApiView(APIView):
    def get(self,_):
        users= User.objects.all()
        user=random.choice(users)
        return Response({
            'id':user.id
        })