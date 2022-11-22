<%--
  Created by IntelliJ IDEA.
  User: denis
  Date: 28/03/2022
  Time: 23:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container">
    <footer class="py-5">
        <div class="row">
            <div class="col-2">
                <form action="notes.html" method="post">
                    <button type="submit" class="btn btn-secondary" name="button" value="add">
                        Add Note
                    </button>
                </form>
            </div>
            <div class="col-4 offset-1">
                <form action="notes.html">
                    <div class="d-flex w-100 gap-2">
                        <label for="newsletter1" class="visually-hidden">Search</label>
                        <input id="newsletter1" name="searchbar" type="text" value="Search" class="form-control">
                        <button class="btn btn-secondary" type="submit">
                            Search
                        </button>
                    </div>
                </form>
            </div>
            <div class="col-4 offset-1">
                <form action="notes.html">
                    <button type="submit" class="btn btn-secondary" name="sort" value="name">
                        Sort by name
                    </button>
                    <button type="submit" class="btn btn-secondary" name="sort" value="date">
                        Sort by date
                    </button>
                </form>
            </div>
        </div>
    </footer>
</div>
<%--<script src="js/bootstrap.bundle.min.js"></script>--%>
