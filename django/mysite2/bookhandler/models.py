from django.db import models

# Create your models here.
class BookUser(models.Model):
    name = models.CharField(max_length=50)


class Genre(models.Model):
    name = models.CharField(max_length=50)
    def __str__(self):
        return self.name

class Book(models.Model):
    tittle = models.CharField(max_length=50)
    author = models.CharField(max_length=50)
    genre = models.ForeignKey(Genre)
    publisher = models.CharField(max_length=50)
    points = models.FloatField(default=0)
    description = models.CharField(max_length=500)
    date = models.DateTimeField(auto_now_add=True)
