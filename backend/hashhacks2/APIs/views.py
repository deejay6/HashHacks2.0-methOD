# -*- coding: utf-8 -*-
from __future__ import unicode_literals
import json
from django.views.decorators.csrf import csrf_exempt
from django.http import JsonResponse
from django.db.models import Q
from django.shortcuts import render
from django.http import HttpResponse
from models import profileModel, loanGivenModel, loanNeededModel
from .utils.lr import getCreditRating
import random
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
        fb = data['facebook']
        gender = data['isMale']
        twitter = data['twitter']
        bool = data['isBorrower']
        phone = data['mobile']
        password = data['password']
        print type(bool), type(phone)
        rating = 'A'
        interest = 5
        ###############################################################
        facebook_twitter = random.choice([0,1])
        postpaid = random.choice([0,1])
        late_bill_days_avg = random.choice([0,1,2,3])
        Education = random.choice([0,1])
        similar_locations = random.choice([0,1,2,3])
        network_bytes = int(random.random()*10000 % 12000)
        daily_sms = int(random.random()*10000 % 6000)
        usage_hours_per_week = int((random.random()*10000) % 10)
        if Education == 1:
            pan = 1
        else:
            pan = random.choice([0, 1])
        Property_Area = random.choice([1, 2, 3])
        #################################################################
        query_object = profileModel(name=name, address=address, aadhar=aadhar,
         dob=dob, facebookID=fb, twitterID=twitter, phone=phone, isBorrower=bool,
          creditRating=rating, interestRate=interest, password=password, gender=gender,
          facebook_twitter = facebook_twitter, postpaid=postpaid, late_bill_days_avg=late_bill_days_avg,
          Education = Education, similar_locations=similar_locations, network_bytes=network_bytes,
          daily_sms = daily_sms, usage_hours_per_week=usage_hours_per_week, pan=pan, Property_Area=Property_Area
          )
        query_object.save()
        resp = {
            'mobile' : phone,
        }
    else:
        resp = {
            'mobile' : 400,
        }

    return JsonResponse(resp, safe=False)

@csrf_exempt
def loginView(request):
    if request.method == "POST":
        print request.body
        data = json.loads(request.body.decode(encoding='UTF-8'))
        phone = data['mobile']

        password = data['password']
        print phone, password
        try:
            profile = profileModel.objects.get(phone=phone)
        except:
            profile = None
        if(profile):
            resp = {
                'isBorrower' : profile.isBorrower,
            }
        else:
            resp = {
                'isBorrower': 400,
            }
    else:
        resp = {
            'isBorrower' : 400,
        }

    return JsonResponse(resp, safe=False)

@csrf_exempt
def isactiveView(request):
    if request.method == "GET":
        phone = request.GET.get('mobile', None)
        print phone
        try:
            query_obj = loanNeededModel.objects.get(personID = phone)
        except:
            query_obj = None
        data = {}
        if query_obj:
            data['isActive'] = True
            data['ID'] = query_obj.pk
            data['purpose'] = query_obj.purpose
            data['amount'] = query_obj.amount
            data['interest'] = query_obj.getInterestRate()[0]
            data['tenure'] = query_obj.tenure
            data['timeLeft'] = query_obj.getTimeLeft() + " days"
            data['amountRemaining'] = query_obj.getRemaining()

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
        phone = request.GET.get('mobile', None)
        print phone
        try:
            profileObj = profileModel.objects.get(phone=phone)
        except:
            profileObj = None
        query_obj = loanNeededModel(purpose=purpose, amount=amount, tenure=tenure, personID=profileObj, interest=profileObj.interestRate, riskCategory=profileObj.creditRating)
        rate,risk = query_obj.getInterestRate()
        query_obj["interest"] = rate
        query_obj["riskCategory"] = risk
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
                temp_obj = loanGivenModel.objects.get(loanID=x, personID = phone)
            except Exception as DoesNotExist:
                adata = {}
                adata['ID'] = x.pk
                print x.pk
                adata['purpose'] = x.purpose
                adata['amount'] = x.amount
                adata['interest'] = x.getInterestRate()[0]
                adata['tenure'] = x.tenure
                adata['timeLeft'] = x.getTimeLeft() + " days"
                adata['amountRemaining'] = x.getRemaining()
                data.append(adata)

        resp = json.dumps(data)
        print resp
        return JsonResponse(json.loads(resp), safe=False)

@csrf_exempt
def loanGivenView(request):
    if request.method == "GET":
        phone = request.GET.get('mobile', None)
        query_obj = loanGivenModel.objects.all()
        profile_obj = profileModel.objects.get(pk=phone)
        data = []
        for x in query_obj:
            if x.personID == profile_obj:
                dic = {}
                dic['ID'] = x.pk
                dic['borrowerID'] = x.loanID.personID.phone
                dic['amount'] = x.amount
                dic['when'] = x.getWhen()
                dic['tenure'] = x.loanID.tenure
                dic['interest'] = x.loanID.getInterestRate()[0]
                data.append(dic)

        resp = json.dumps(data)
        print resp
        return JsonResponse(json.loads(resp), safe=False)

@csrf_exempt
def paymentView(request):
    if request.method == "POST":
        data = json.loads(request.body.decode(encoding='UTF-8'))
        phone = request.GET.get('mobile', None)
        amount = data['amount']
        id = data['loanId']
        profile_obj = profileModel.objects.get(pk=phone)
        loan_obj = loanNeededModel.objects.get(pk=id)
        query_obj = loanGivenModel(amount=amount, loanID=loan_obj, personID=profile_obj)
        query_obj.save()
        resp = {
            'loanId' : 200,
        }
    else:
        resp = {
            'loanId' : 400,
        }

    return JsonResponse(resp, safe=False)
