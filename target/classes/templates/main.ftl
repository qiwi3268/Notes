<#import "parts/frame.ftl" as f>
<@f.frame>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="navbar-brand" href="/">All Notes</a>
            </li>
            <li class="nav-item">
                <a class="navbar-brand" href="/add">Add</a>
            </li>
            <li class="nav-item">
                <form class="form-inline" method="post" >
                    <input class="form-control mr-sm-2" type="search" name="filter" placeholder="Search by notes" aria-label="Search"/>
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                </form>
            </li>
        </ul>
    </nav>
    <div class="note-container" id="note-list">
        <#list notes as note>
            <div class="input-group mb-2 mr-sm-2 m-3" data-id="${note.id}">
                <a class="input-item w-25" href="/note/${note.id}">
                    <input class="form-control rounded-0" type="text" placeholder="${note.firstLine}" readonly="readonly"/>
                </a>
                <div class="input-group-prepend">
                    <a class="input-group-text btn btn-danger" href="/delete/${note.id}">Delete</a>
                </div>
            </div>
        </#list>
    </div>
</@f.frame>