"""hashhacks2 URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/1.11/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  url(r'^$', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  url(r'^$', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.conf.urls import url, include
    2. Add a URL to urlpatterns:  url(r'^blog/', include('blog.urls'))
"""
from django.conf.urls import url
from django.contrib import admin
from APIs.views import signupView, loginView, isactiveView, createLoanView, loanDisplayView, loanGivenView, paymentView, borrowerProfile
urlpatterns = [
    url(r'^api/payment', paymentView),
    url(r'^api/loangiven', loanGivenView),
    url(r'^api/loandisplay', loanDisplayView),
    url(r'^api/createloan', createLoanView),
    url(r'^api/isactive', isactiveView),
    url(r'^api/signup', signupView),
    url(r'^api/login', loginView),
    url(r'^api/profile', borrowerProfile),
    url(r'^admin/', admin.site.urls),
]
