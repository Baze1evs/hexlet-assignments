@import gg.jte.Content

@param Content title
@param Content content

<head>
    <title>${title}</title>
</head>
<body>
    <div class="header">
        <h1><a href="/">Main</a><a href="/users">Users</a></h1>
    </div>
    <div class="content">
        ${content}
    </div>
    <div class="footer">
        <h1>Footer</h1>
    </div>
</body>