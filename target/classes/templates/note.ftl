<#import "parts/frame.ftl" as f>
<@f.frame>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="navbar-brand" href="/">All Notes</a>
            </li>
        </ul>
    </nav>
    <form method="post">
        <#include "parts/form.ftl">
        <button type="submit" class="btn btn-primary ml-2">OK</button>
        <a class="btn btn-primary" href="/">Back</a>
        <a class="btn btn-primary" href="/delete/${note.id}">Delete</a>
    </form>
</@f.frame>