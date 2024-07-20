# Use Case

The use case of this project is scheduling e-mails periodically. Personally, I wanted to send static e-mails periodically to my landlord asking for a car park as they were unavailable when moving in. They don't keep track of people asking having no waiting list and encouraged me to ask once a month.

### Configuration

Create an application(-local).yml file with your e-mail credentials. Add a server context-path and port.

```
email:
  username: email@name.de
  password: xxx
  sendTo: email@recipient.de
  bcc: email@recipient2.de
  bccEnabled: true
  period: 1814400000 # 3 weeks in milliseconds
  host: smpt.host.de
  port: 587
  template:
    subject: your subject
    path: template.html
    name: recipient
```

In the resources/templates directory create an email-template.html file containing your e-mail credentials.


