from django.conf.urls import include, url
from django.contrib import admin

urlpatterns = [
        url(r'^polls/', include('polls.urls', namespace="polls")),
        url(r'^bookhandler/', include('bookhandler.urls', namespace="bookhandler")),
        url(r'^admin/', include(admin.site.urls)),
        ]
