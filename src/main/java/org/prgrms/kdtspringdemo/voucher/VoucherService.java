package org.prgrms.kdtspringdemo.voucher;

import org.prgrms.kdtspringdemo.voucher.model.Voucher;
import org.prgrms.kdtspringdemo.voucher.model.VoucherType;
import org.prgrms.kdtspringdemo.voucher.repository.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.UUID;
@Service
public class VoucherService {
    private final VoucherRepository voucherRepository;
    private final VoucherCreator voucherCreator;

    @Autowired
    public VoucherService(VoucherRepository voucherRepository, VoucherCreator voucherCreator) {
        this.voucherRepository = voucherRepository;
        this.voucherCreator = voucherCreator;
    }

    public Voucher getVoucher(UUID voucherId) {
        return voucherRepository.findById(voucherId).orElseThrow(() ->
                new NullPointerException(MessageFormat.format("Can not find a voucher for{0}", voucherId)));
    }

    public Voucher createVoucher(VoucherType voucherType, Long number) throws IllegalArgumentException, IllegalStateException {
        Voucher newVoucher = voucherCreator.createVoucher(voucherType, number);
        var voucher = voucherRepository.insert(newVoucher);
        if (voucher.isPresent()) {
            return voucher.get();
        } else {
            return null;
        }
    }

    public List<Voucher> getAllVoucherList() {
        return voucherRepository.findAllVaucher();
    }
}
