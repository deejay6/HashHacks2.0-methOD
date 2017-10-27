# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models

# Create your models here.
class profileModel(models.Model):
    name = models.CharField(max_length=20)
    aadhar = models.CharField(max_length=20)
    gender = models.CharField(max_length=10)
    address = models.CharField(max_length=50)
    phone = models.CharField(max_length=20, primary_key=True)
    dob = models.CharField(max_length=20)
    facebookID = models.CharField(max_length=50)
    twitterID = models.CharField(max_length=50)
    creditRating = models.IntegerField(default=0)
    interestRate = models.IntegerField(default=0)
    isBorrower = models.BooleanField(default=True)

class loanGivenModel(models.Model):
    personID = models.ForeignKey(profileModel, on_delete=models.CASCADE)
    amount = models.IntegerField(default=0)
    createdOn = models.DateTimeField(auto_now_add=True)

class loanNeededModel(models.Model):
    purpose = models.CharField(max_length=50)
    personID = models.ForeignKey(profileModel, on_delete=models.CASCADE)
    loanGivenID = models.ForeignKey(loanGivenModel, on_delete=models.CASCADE)
    amount = models.IntegerField(default=0)
    interest = models.IntegerField(default=0)
    tenure = models.IntegerField(default=0)
    riskCategory = models.CharField(max_length=5)
    timestamp = models.DateTimeField(auto_now_add=True)
