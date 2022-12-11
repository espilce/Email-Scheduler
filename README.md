# Use Case

The general use case of this project is scheduling e-mails periodically. Personally, I wanted to send static e-mails periodically to my landlord asking for a car park as they were unavailable when moving in. They don't keep track of people asking basically having no waiting list.

### Configuration

Create a application-local.yml file with your e-mail credentials. Add a server context-path and port

```
email:  
  username: mail@domain.com  
  password: **************** 
  sendTo: mail@recipient.com  
  period: 10000000000000
  template:  
    subject: mail-subject 
    path: email-template.html  
    name: recipient name 
```

Create a templates/email-template.html file containing your e-mail contents.

```
<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml" xmlns:o="urn:schemas-microsoft-com:office:office">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <meta name="x-apple-disable-message-reformatting">
    <title></title>
    <!--[if mso]>
    <noscript>
        <xml>
            <o:OfficeDocumentSettings>
                <o:PixelsPerInch>96</o:PixelsPerInch>
            </o:OfficeDocumentSettings>
        </xml>
    </noscript>
    <![endif]-->
    <style>
        table, td, div, h1, p {
            font-family: Arial, sans-serif;
        }
    </style>
</head>
<body style="margin:0;padding:0;">
<table role="presentation" style="width:100%;border-collapse:collapse;border:0;border-spacing:0;background:#ffffff;">
    <tr>
        <td align="left" style="padding:0;">
            <p>CONTENT</p>
        </td>
    </tr>
</table>
</body>
</html>
```


