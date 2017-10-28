# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.contrib import admin
from .models import profileModel, loanGivenModel, loanNeededModel

# Register your models here.
admin.site.register(profileModel)
admin.site.register(loanNeededModel)
admin.site.register(loanGivenModel)
