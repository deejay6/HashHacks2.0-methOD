# -*- coding: utf-8 -*-
from __future__ import unicode_literals
import json
from django.views.decorators.csrf import csrf_exempt
from django.http import JsonResponse
from django.db.models import Q
from django.shortcuts import render
from models import profileModel, loanGivenModel, loanNeededModel
# Create your views here.



@csrf_exempt
def signupView(request):
    if request.method == "POST":
        print request.body
        data = json.loads(request.body.decode(encoding='UTF-8'))
        name = data['name']
        address = data['address']
        aadhar = data['aadhar']
        dob = data['dob']
        fb = data['fb']
        twitter = data['twitter']
        bool = data['bool']
        phone = data['phone']
        print bool.type(), phone.type()
        rating = 5
        interest = 5
        query_object = profileModel(name=name, address=address, aadhar=aadhar, dob=dob, facebookID=fb, twitterID=twitter, phone=phone, isBorrower=bool, creditRating=rating, interestRate=interest)
        query_object.save()
        resp = {
            'code' : 200,
        }
    else:
        resp = {
            'code' : 200,
        }

    return JsonResponse(resp, safe=False)


