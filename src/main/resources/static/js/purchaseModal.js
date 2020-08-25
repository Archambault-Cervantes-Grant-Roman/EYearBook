//Due to how HTML5 defines its semantics, the autofocus HTML attribute has no effect in Bootstrap modals. To achieve the same effect, use some custom JavaScript:

$('#myModal').on('shown.bs.modal', function () {
    $('#myInput').trigger('focus')
})

