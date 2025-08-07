DB 수정사항.md

-- JellyPurchase : payment nullable=true / 결제요청상태일때 payment 없음
@ManyToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;
