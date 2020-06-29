package com.szubov.android_hw_52;

import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PaymentFormActivity extends AppCompatActivity {

    private EditText mEditTextSumForPayment;
    private EditText mEditTextInfoAboutPayment;
    private CheckBox mCheckBoxPaymentFromBankCard;
    private CheckBox mCheckBoxPaymentByMobilePhone;
    private CheckBox mCheckBoxPaymentCashAtAddress;
    private Button mBtnPaymentOk;
    private Button mBtnReturnFromPaymentForm;
    private static final String TAG = "MyApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_form);

        mEditTextSumForPayment = findViewById(R.id.editTextSumForPayment);
        mEditTextInfoAboutPayment = findViewById(R.id.editTextInfoAboutPayment);
        mCheckBoxPaymentFromBankCard = findViewById(R.id.checkBoxPaymentFromBankCard);
        mCheckBoxPaymentByMobilePhone = findViewById(R.id.checkBoxPaymentByMobilePhone);
        mCheckBoxPaymentCashAtAddress = findViewById(R.id.checkBoxPaymentCashAtAddress);
        mBtnPaymentOk = findViewById(R.id.btnPaymentOk);
        mBtnReturnFromPaymentForm = findViewById(R.id.btnReturnFromPaymentForm);

        mCheckBoxPaymentFromBankCard.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.i(TAG, "User selected checkBox paymentFromBankCard in PaymentFormActivity");
                resetCheckBoxesAndPaymentInfoFiled();
                mCheckBoxPaymentFromBankCard.setChecked(isChecked);
                mEditTextInfoAboutPayment.setInputType(InputType.TYPE_CLASS_NUMBER);
                mEditTextInfoAboutPayment.setHint(R.string.hint_info_about_payment_card_number);
            }
        });

        mCheckBoxPaymentByMobilePhone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.i(TAG, "User selected checkBox paymentByMobilePhone in PaymentFormActivity");
                resetCheckBoxesAndPaymentInfoFiled();
                mCheckBoxPaymentByMobilePhone.setChecked(isChecked);
                mEditTextInfoAboutPayment.setInputType(InputType.TYPE_CLASS_PHONE);
                mEditTextInfoAboutPayment.setHint(R.string.hint_info_about_payment_mobile_number);
            }
        });

        mCheckBoxPaymentCashAtAddress.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.i(TAG, "User selected checkBox paymentCashAtAddress in PaymentFormActivity");
                resetCheckBoxesAndPaymentInfoFiled();
                mCheckBoxPaymentCashAtAddress.setChecked(isChecked);
                mEditTextInfoAboutPayment.setInputType(InputType.TYPE_CLASS_TEXT);
                mEditTextInfoAboutPayment.setHint(R.string.hint_info_about_payment_cash_at_address);
            }
        });

        mBtnPaymentOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "User clicked btn paymentOk in PaymentFormActivity");
                while (true) {
                    if (mEditTextSumForPayment.length() < 1 ||
                            mEditTextInfoAboutPayment.length() <1) {
                        Toast.makeText(PaymentFormActivity.this,
                                getText(R.string.field_is_empty).toString(),
                                Toast.LENGTH_LONG).show();
                        break;
                    } else if (mEditTextInfoAboutPayment.length() >= 100) {
                        Toast.makeText(PaymentFormActivity.this,
                                getText(R.string.exception_btn_ok_save).toString(),
                                Toast.LENGTH_LONG).show();
                        break;
                    } else if (mEditTextSumForPayment.length() >= 15) {
                        Toast.makeText(PaymentFormActivity.this,
                                getText(R.string.field_sum_contains_too_big_value).toString(),
                                Toast.LENGTH_LONG).show();
                        break;
                    }else if (mEditTextInfoAboutPayment.getText().toString().
                            replaceAll("\\s+", "").length() < 1) {
                        Toast.makeText(PaymentFormActivity.this,
                                getText(R.string.field_info_for_payment_contains_invalid_values).
                                toString(),
                                Toast.LENGTH_LONG).show();
                    } else {
                        try {
                            if (mCheckBoxPaymentFromBankCard.isChecked()) {
                                Toast.makeText(PaymentFormActivity.this,
                                        getText(R.string.hint_sum_for_payment).toString()
                                        + ": " + mEditTextSumForPayment.getText().toString()
                                        + "\n" + getText(R.string.payment_method).toString() + ": "
                                        + getText(R.string.check_box_card_payment).toString()
                                        + "\n" + getText(R.string.hint_info_about_payment_card_number).
                                        toString() + ": "
                                        + mEditTextInfoAboutPayment.getText().toString(),
                                        Toast.LENGTH_LONG).show();
                                resetPaymentForm();
                            } else if (mCheckBoxPaymentByMobilePhone.isChecked()) {
                                Toast.makeText(PaymentFormActivity.this,
                                        getText(R.string.hint_sum_for_payment).toString()
                                        + ": " + mEditTextSumForPayment.getText().toString()
                                        + "\n" + getText(R.string.payment_method).toString() + ": "
                                        + getText(R.string.check_box_mobile_phone_payment).toString()
                                        + "\n" + getText(R.string.hint_info_about_payment_mobile_number).
                                        toString() + ": "
                                        + mEditTextInfoAboutPayment.getText().toString().
                                        replaceAll("\\s+", " ").trim(),
                                        Toast.LENGTH_LONG).show();
                                resetPaymentForm();
                            } else {
                                Toast.makeText(PaymentFormActivity.this,
                                        getText(R.string.hint_sum_for_payment).toString()
                                        + ": " + mEditTextSumForPayment.getText().toString()
                                        + "\n" + getText(R.string.payment_method).toString() + ": "
                                        + getText(R.string.check_box_payment_cash_at_address).toString()
                                        + "\n" + getText(R.string.hint_info_about_payment_cash_at_address).
                                        toString() + ": "
                                        + mEditTextInfoAboutPayment.getText().toString().
                                        replaceAll("\\s+", " ").trim(),
                                        Toast.LENGTH_LONG).show();
                                resetPaymentForm();
                            }
                        } catch (NumberFormatException ex) {
                            Log.e(TAG, "Btn paymentOk exception in PaymentFormActivity", ex);
                            Toast.makeText(PaymentFormActivity.this,
                                    R.string.exception_btn_ok_save, Toast.LENGTH_LONG).show();
                        }
                    }
                    break;
                }
            }
        });

        mBtnReturnFromPaymentForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void resetCheckBoxesAndPaymentInfoFiled() {
        mCheckBoxPaymentFromBankCard.setChecked(false);
        mCheckBoxPaymentByMobilePhone.setChecked(false);
        mCheckBoxPaymentCashAtAddress.setChecked(false);
        mEditTextInfoAboutPayment.setText(null);
    }

    private void resetPaymentForm() {
        mEditTextSumForPayment.setText(null);
        mEditTextInfoAboutPayment.setText(null);
        mCheckBoxPaymentFromBankCard.setChecked(true);
    }
}