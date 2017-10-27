# -*- coding: utf-8 -*-
from __future__ import unicode_literals
import json
from django.views.decorators.csrf import csrf_exempt
from django.http import JsonResponse
from django.db.models import Q
from django.shortcuts import render
from models import profileModel, loanGivenModel, loanNeededModel
# Create your views here.

x = 0

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
        password = data['password']
        print type(bool), type(phone)
        rating = 'A'
        interest = 5
        query_object = profileModel(name=name, address=address, aadhar=aadhar, dob=dob, facebookID=fb, twitterID=twitter, phone=phone, isBorrower=bool, creditRating=rating, interestRate=interest, password=password)
        query_object.save()
        resp = {
            'code' : 200,
        }
    else:
        resp = {
            'code' : 400,
        }

    return JsonResponse(resp, safe=False)

@csrf_exempt
def loginView(request):
    if request.method == "POST":
        print request.body
        data = json.loads(request.body.decode(encoding='UTF-8'))
        phone = data['phone']

        password = data['password']
        print phone, password
        try:
            profile = profileModel.objects.get(phone=phone)
        except:
            profile = None
        if(profile):
            resp = {
                'bool' : profile.isBorrower,
            }
        else:
            resp = {
                'code': 400,
            }
    else:
        resp = {
            'code' : 400,
        }

    return JsonResponse(resp, safe=False)

@csrf_exempt
def isactiveView(request):
    if request.method == "GET":
        phone = request.GET.get('phone', None)
        query_obj = loanNeededModel.objects.filter(personID = phone)
        data = {}
        if query_obj.exists():
            data = query_obj.first()
            data['isActive'] = True
            print data
        else:
            data['isActive'] = False
        return JsonResponse(data, safe=False)
    else:
        resp = {
            "code" : 400,
        }
    return JsonResponse(resp, safe=False)

@csrf_exempt
def createLoanView(request):
    if request.method == "POST":
        data = json.loads(request.body.decode(encoding='UTF-8'))
        purpose = data['purpose']
        amount = data['amount']
        tenure = data['tenure']
        phone = request.GET.get('phone', ' ')
        try:
            profileObj = profileModel.objects.get(phone=phone)
        except:
            profileObj = None
        query_obj = loanNeededModel(purpose=purpose, amount=amount, tenure=tenure, personID=profileObj, interest=profileObj.interest, riskCategory=profileObj.creditRating)
        query_obj.save()
        resp = {
            'id' : phone,
        }
    else:
        resp = {
            'code' : 400,
        }

    return JsonResponse(resp, safe=False)

@csrf_exempt
def loanDisplayView(request):
    if request.method == "GET":
        phone = request.GET.get('phone', None)
        query_obj = loanNeededModel.objects.all()
        data = []
        for x in query_obj:
            try:
                temp_obj = loanGivenModel.object.get(loanID=x, personID = phone)
            except:
                temp_obj = None
            if temp_obj == None:
                data.append(x)

        resp = json.dumps(data)
        print resp
        return JsonResponse(json.loads(resp), safe=False)

@csrf_exempt
def loanGivenView(request):
    if request.method == "GET":
        phone = request.GET.get('phone', None)
        query_obj = loanGivenModel.objects.all()
        data = []
        for x in query_obj:
            if x.personID == phone:
                data.append(x)

        resp = json.dumps(data)
        print resp
        return JsonResponse(json.loads(resp), safe=False)

@csrf_exempt
def paymentView(request):
    if request.method == "POST":
        data = json.loads(request.body.decode(encoding='UTF-8'))
        phone = request.GET.get('phone', None)
        amount = data['amount']
        id = data['id']
        query_obj = loanGivenModel.objects.get(pk=id)
        loan_obj = loanNeededModel.objects.get(personID=query_obj.personID)
        loan_obj.amount = loan_obj.amount - amount
        resp = {
            'code' : 200,
        }
    else:
        resp = {
            'code' : 400,
        }

    return JsonResponse(resp, safe=False)
