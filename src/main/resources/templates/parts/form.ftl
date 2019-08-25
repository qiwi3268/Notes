<div class="form-group m-2 w-25">
    <input type="text" class="form-control ${(error?? || headerError??)?string('is-invalid', '')}" name="header"
           value="<#if note??>${note.header}</#if>" id="exampleFormControlInput1" placeholder="Input header"/>
    <#if error?? || headerError??>
        <div class="invalid-feedback">
            <#if error??>
                ${error}
            <#else>
                ${headerError}
            </#if>
        </div>
    </#if>
</div>
<div class="form-group m-2 w-25">
        <textarea class="form-control ${(error?? || contentError??)?string('is-invalid', '')}" name="content"
                  rows="10" id="validationTextarea" placeholder="Input text"><#if note??>${note.content}</#if></textarea>
    <#if error?? || contentError??>
        <div class="invalid-feedback">
            <#if error??>
                ${error}
            <#else>
                ${contentError}
            </#if>
        </div>
    </#if>
</div>
