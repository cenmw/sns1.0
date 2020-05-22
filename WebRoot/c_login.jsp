<script type="text/javascript" src="/common/js/jquery-1.4.js"></script>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<script type="text/javascript">
    function goIndex(){
        window.location.href = location.href="/index";
    }
</script>
<style>
    .alert {
        display: none;
        position: fixed;
        top: 30%;
        left: 50%;
        min-width: 200px;
        margin-left: -100px;
        z-index: 99999;
        padding: 15px;
        border: 1px solid transparent;
        border-radius: 4px;
        font-size: larger;
    }
    .alert-danger {
        color: #a94442;
        background-color: #f2dede;
        border-color: #ebccd1;
    }
</style>
<div class="alert"></div>
<script type="text/javascript">$('.alert').html('您还没有登录或登录已失效！').addClass('alert-danger').show().delay(1500).fadeOut();setTimeout(goIndex,1500);</script>