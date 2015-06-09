from django.conf.urls import url

from . import views


urlpatterns = [
        url(r'^login/$', 'django.contrib.auth.views.login', name='login'),
        ]


#        url(r'^login/$',    views.login_user, name='login'),
