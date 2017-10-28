# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models
from datetime import datetime
import pytz
import random
from .utils.lr import getCreditRating
# from .utils.socialScore.py import calculateSS

# Create your models here.
class profileModel(models.Model):
    name = models.CharField(max_length=20)
    aadhar = models.CharField(max_length=20)
    gender = models.BooleanField(default=True)
    address = models.CharField(max_length=50)
    phone = models.CharField(max_length=50, primary_key=True)
    dob = models.CharField(max_length=20)
    facebookID = models.CharField(max_length=50)
    twitterID = models.CharField(max_length=50)
    password = models.CharField(max_length=50)
    creditRating = models.CharField(max_length=5)
    interestRate = models.IntegerField(default=0)
    isBorrower = models.BooleanField(default=True)

    ######################BAAD KE FIELDS
    facebook_twitter = models.IntegerField(null=True, blank=True)
    postpaid = models.IntegerField(null=True, blank=True)
    late_bill_days_avg = models.IntegerField(null=True, blank=True)
    Education = models.IntegerField(null=True, blank=True)
    similar_locations = models.IntegerField(null=True, blank=True)
    network_bytes = models.IntegerField(null=True, blank=True)
    daily_sms = models.IntegerField(null=True, blank=True)
    usage_hours_per_week = models.IntegerField(null=True, blank=True)
    pan = models.IntegerField(null=True, blank=True)
    Property_Area = models.IntegerField(null=True, blank=True)

    def __str__(self):
        return self.name

class loanNeededModel(models.Model):
    purpose = models.CharField(max_length=50)
    personID = models.ForeignKey(profileModel, on_delete=models.CASCADE)
    amount = models.IntegerField(default=0)
    interest = models.IntegerField(default=0)
    tenure = models.IntegerField(default=0)
    riskCategory = models.CharField(max_length=5)
    timestamp = models.DateTimeField(auto_now_add=True)

    def __str__(self):
        return self.purpose + " by " + self.personID.name

    def getRemaining(self):
        allGotTillNow = loanGivenModel.objects.filter(loanID = self.id)
        tillNow = 0
        for given in allGotTillNow:
            tillNow += given.amount
        return self.amount - tillNow

    def getTimeLeft(self):
        left = pytz.utc.localize(datetime.today()) - self.timestamp
        if left.days <=15:
            return str(15 - left.days)
        else:
            return "0"
    
    def getInterestRate(self, check=1, amo=0):
        aadmi = self.personID
        if check == 1:
            am = self.amount
        else:
            am = amo
        person = [aadmi.facebook_twitter,
            aadmi.postpaid,
            aadmi.late_bill_days_avg,
            aadmi.Education,
            aadmi.similar_locations,
            aadmi.network_bytes,
            aadmi.daily_sms,
            am,
            aadmi.usage_hours_per_week,
            aadmi.pan,
            aadmi.Property_Area]
        rate, riskCategory = getCreditRating(person)
        return (rate, riskCategory)

    def setStuff(self, rate, risk):
        self.interest = rate
        self.riskCategory = risk

class loanGivenModel(models.Model):
    personID = models.ForeignKey(profileModel, on_delete=models.CASCADE)
    amount = models.IntegerField(default=0)
    loanID = models.ForeignKey(loanNeededModel, on_delete=models.CASCADE)
    createdOn = models.DateTimeField(auto_now_add=True)

    def __str__(self):
        return self.loanID.purpose + " given by " + self.personID.name
    
    def getWhen(self):
        return datetime.strftime(self.createdOn, '%d/%m/%y')
