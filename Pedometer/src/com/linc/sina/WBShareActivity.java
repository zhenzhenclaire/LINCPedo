/*
 * Copyright (C) 2010-2013 The SINA WEIBO Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.linc.sina;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.claire.pedometer.R;
import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.MusicObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.VideoObject;
import com.sina.weibo.sdk.api.VoiceObject;
import com.sina.weibo.sdk.api.WebpageObject;
import com.sina.weibo.sdk.api.WeiboMessage;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.api.share.BaseResponse;
import com.sina.weibo.sdk.api.share.IWeiboDownloadListener;
import com.sina.weibo.sdk.api.share.IWeiboHandler;
import com.sina.weibo.sdk.api.share.IWeiboShareAPI;
import com.sina.weibo.sdk.api.share.SendMessageToWeiboRequest;
import com.sina.weibo.sdk.api.share.SendMultiMessageToWeiboRequest;
import com.sina.weibo.sdk.api.share.WeiboShareSDK;
import com.sina.weibo.sdk.constant.WBConstants;
import com.sina.weibo.sdk.exception.WeiboShareException;
import com.sina.weibo.sdk.utils.Utility;

/**
 * ������ʾ�˵�����Ӧ�����ͨ��΢���ͻ��˷������֡�ͼƬ����Ƶ�����ֵȡ�
 * ִ�����̣� �ӱ�Ӧ��->΢��->��Ӧ��
 * 
 * @author SINA
 * @since 2013-10-22
 */
public class WBShareActivity extends Activity implements OnClickListener, IWeiboHandler.Response {
    @SuppressWarnings("unused")
    private static final String TAG = "WBShareActivity";

   
    private Button          mSharedBtn;
    
    /** ΢��΢������ӿ�ʵ�� */
    private IWeiboShareAPI  mWeiboShareAPI = null;

    /**
     * @see {@link Activity#onCreate}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sina);
        initViews();

        // ����΢������ӿ�ʵ��
        mWeiboShareAPI = WeiboShareSDK.createWeiboAPI(this, Constants.APP_KEY);
        
        // ע�������Ӧ�õ�΢���ͻ����У�ע��ɹ����Ӧ�ý���ʾ��΢����Ӧ���б��С�
        // ���ø��������ɷ���Ȩ����Ҫ�������룬������鿴 Demo ��ʾ
        // NOTE���������ǰע�ᣬ�������ʼ����ʱ�����Ӧ�ó����ʼ��ʱ������ע��
        mWeiboShareAPI.registerApp();
        
       
        
		// �� Activity �����³�ʼ��ʱ���� Activity ���ں�̨ʱ�����ܻ������ڴ治�㱻ɱ���ˣ���
        // ��Ҫ���� {@link IWeiboShareAPI#handleWeiboResponse} ������΢���ͻ��˷��ص����ݡ�
        // ִ�гɹ������� true�������� {@link IWeiboHandler.Response#onResponse}��
        // ʧ�ܷ��� false�������������ص�
        if (savedInstanceState != null) {
            mWeiboShareAPI.handleWeiboResponse(getIntent(), this);
        }
    }
    
    /**
     * @see {@link Activity#onNewIntent}
     */	
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        
        // �ӵ�ǰӦ�û���΢�������з���󣬷��ص���ǰӦ��ʱ����Ҫ�ڴ˴����øú���
        // ������΢���ͻ��˷��ص����ݣ�ִ�гɹ������� true��������
        // {@link IWeiboHandler.Response#onResponse}��ʧ�ܷ��� false�������������ص�
        mWeiboShareAPI.handleWeiboResponse(intent, this);
    }

    /**
     * ����΢�ͻ��˲���������ݡ�
     * ��΢���ͻ��˻���ǰӦ�ò����з���ʱ���÷��������á�
     * 
     * @param baseRequest ΢���������ݶ���
     * @see {@link IWeiboShareAPI#handleWeiboRequest}
     */
    @Override
    public void onResponse(BaseResponse baseResp) {
        switch (baseResp.errCode) {
        case WBConstants.ErrorCode.ERR_OK:
            Toast.makeText(this, "success", Toast.LENGTH_LONG).show();
            break;
        case WBConstants.ErrorCode.ERR_CANCEL:
            Toast.makeText(this, "canceled", Toast.LENGTH_LONG).show();
            break;
        case WBConstants.ErrorCode.ERR_FAIL:
            Toast.makeText(this, 
                    "failed" + "Error Message: " + baseResp.errMsg, 
                    Toast.LENGTH_LONG).show();
            break;
        }
    }

    /**
     * �û��������ť������΢���ͻ��˽��з���
     */
    @Override
    public void onClick(View v) {
        if (R.id.share == v.getId()) {
            try {
            	
                // ���΢���ͻ��˻����Ƿ����������δ��װ΢���������Ի���ѯ���û�����΢���ͻ���
                if (mWeiboShareAPI.checkEnvironment(true)) {    
                	System.out.println("click");
                    sendMessage(false, 
                    		false, 
                    		false,
                    		false, 
                    		false, 
                    		false);
                }
            } catch (WeiboShareException e) {
                e.printStackTrace();
                Toast.makeText(WBShareActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }

    /**
     * ��ʼ�����档
     */
    private void initViews() {
       
        mSharedBtn = (Button) findViewById(R.id.share);
        mSharedBtn.setOnClickListener(this);
        
       
    }


    /**
     * ������Ӧ�÷���������Ϣ��΢��������΢��������档
     * @see {@link #sendMultiMessage} ���� {@link #sendSingleMessage}
     */
    private void sendMessage(boolean hasText, boolean hasImage, 
			boolean hasWebpage, boolean hasMusic, boolean hasVideo, boolean hasVoice) {
        
        if (mWeiboShareAPI.isWeiboAppSupportAPI()) {
            int supportApi = mWeiboShareAPI.getWeiboAppSupportAPI();
            if (supportApi >= 10351 /*ApiUtils.BUILD_INT_VER_2_2*/) {
                sendMultiMessage(hasText, hasImage, hasWebpage, hasMusic, hasVideo, hasVoice);
            } else {
                sendSingleMessage(hasText, hasImage, hasWebpage, hasMusic, hasVideo/*, hasVoice*/);
            }
        } else {
            Toast.makeText(this, "weibosdk_demo_not_support_api_hint", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * ������Ӧ�÷���������Ϣ��΢��������΢��������档
     * ע�⣺�� {@link IWeiboShareAPI#getWeiboAppSupportAPI()} >= 10351 ʱ��֧��ͬʱ���������Ϣ��
     * ͬʱ���Է����ı���ͼƬ�Լ�����ý����Դ����ҳ�����֡���Ƶ�������е�һ�֣���
     * 
     * @param hasText    ����������Ƿ����ı�
     * @param hasImage   ����������Ƿ���ͼƬ
     * @param hasWebpage ����������Ƿ�����ҳ
     * @param hasMusic   ����������Ƿ�������
     * @param hasVideo   ����������Ƿ�����Ƶ
     * @param hasVoice   ����������Ƿ�������
     */
    private void sendMultiMessage(boolean hasText, boolean hasImage, boolean hasWebpage,
            boolean hasMusic, boolean hasVideo, boolean hasVoice) {
        
    	System.out.println("send mutilpe");
        // 1. ��ʼ��΢���ķ�����Ϣ
        WeiboMultiMessage weiboMessage = new WeiboMultiMessage();

            weiboMessage.textObject = getTextObj();
        
        
        if (hasImage) {
            weiboMessage.imageObject = getImageObj();
        }
        
        // �û����Է�������ý����Դ����ҳ�����֡���Ƶ�������е�һ�֣�
        if (hasWebpage) {
            weiboMessage.mediaObject = getWebpageObj();
        }
        if (hasMusic) {
            weiboMessage.mediaObject = getMusicObj();
        }
        if (hasVideo) {
            weiboMessage.mediaObject = getVideoObj();
        }
        if (hasVoice) {
            weiboMessage.mediaObject = getVoiceObj();
        }
        
        // 2. ��ʼ���ӵ�������΢������Ϣ����
        SendMultiMessageToWeiboRequest request = new SendMultiMessageToWeiboRequest();
        // ��transactionΨһ��ʶһ������
        request.transaction = String.valueOf(System.currentTimeMillis());
        request.multiMessage = weiboMessage;
        
        // 3. ����������Ϣ��΢��������΢���������
        mWeiboShareAPI.sendRequest(request);
    }

    /**
     * ������Ӧ�÷���������Ϣ��΢��������΢��������档
     * ��{@link IWeiboShareAPI#getWeiboAppSupportAPI()} < 10351 ʱ��ֻ֧�ַ�������Ϣ����
     * �ı���ͼƬ����ҳ�����֡���Ƶ�е�һ�֣���֧��Voice��Ϣ��
     * 
     * @param hasText    ����������Ƿ����ı�
     * @param hasImage   ����������Ƿ���ͼƬ
     * @param hasWebpage ����������Ƿ�����ҳ
     * @param hasMusic   ����������Ƿ�������
     * @param hasVideo   ����������Ƿ�����Ƶ
     */
    private void sendSingleMessage(boolean hasText, boolean hasImage, boolean hasWebpage,
            boolean hasMusic, boolean hasVideo/*, boolean hasVoice*/) {
    	System.out.println("send single");
        // 1. ��ʼ��΢���ķ�����Ϣ
        // �û����Է����ı���ͼƬ����ҳ�����֡���Ƶ�е�һ��
        WeiboMessage weiboMessage = new WeiboMessage();
        if (hasText) {
            weiboMessage.mediaObject = getTextObj();
        }
        if (hasImage) {
            weiboMessage.mediaObject = getImageObj();
        }
        if (hasWebpage) {
            weiboMessage.mediaObject = getWebpageObj();
        }
        if (hasMusic) {
            weiboMessage.mediaObject = getMusicObj();
        }
        if (hasVideo) {
            weiboMessage.mediaObject = getVideoObj();
        }
        /*if (hasVoice) {
            weiboMessage.mediaObject = getVoiceObj();
        }*/
        
        // 2. ��ʼ���ӵ�������΢������Ϣ����
        SendMessageToWeiboRequest request = new SendMessageToWeiboRequest();
        // ��transactionΨһ��ʶһ������
        request.transaction = String.valueOf(System.currentTimeMillis());
        request.message = weiboMessage;
        
        // 3. ����������Ϣ��΢��������΢���������
        mWeiboShareAPI.sendRequest(request);
    }

    /**
     * ��ȡ������ı�ģ�塣
     * 
     * @return ������ı�ģ��
     */
    private String getSharedText() {
        return "  share ";
    }

    /**
     * �����ı���Ϣ����
     * 
     * @return �ı���Ϣ����
     */
    private TextObject getTextObj() {
        TextObject textObject = new TextObject();
        textObject.text = getSharedText();
        return textObject;
    }

    /**
     * ����ͼƬ��Ϣ����
     * 
     * @return ͼƬ��Ϣ����
     */
    private ImageObject getImageObj() {
    	
        ImageObject imageObject = new ImageObject();
        BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources().openRawResource(R.drawable.ic_launcher));  
        imageObject.setImageObject(bitmapDrawable.getBitmap());
        return imageObject;

    }

    /**
     * ������ý�壨��ҳ����Ϣ����
     * 
     * @return ��ý�壨��ҳ����Ϣ����
     */
    private WebpageObject getWebpageObj() {
    	/*
        WebpageObject mediaObject = new WebpageObject();
        mediaObject.identify = Utility.generateGUID();
        mediaObject.title = mShareWebPageView.getTitle();
        mediaObject.description = mShareWebPageView.getShareDesc();
        
        // ���� Bitmap ���͵�ͼƬ����Ƶ������
        mediaObject.setThumbImage(mShareWebPageView.getThumbBitmap());
        mediaObject.actionUrl = mShareWebPageView.getShareUrl();
        mediaObject.defaultText = "Webpage Ĭ���İ�";
        return mediaObject;*/
    	return null;
    }

    /**
     * ������ý�壨���֣���Ϣ����
     * 
     * @return ��ý�壨���֣���Ϣ����
     */
    private MusicObject getMusicObj() {
    	/*
        // ����ý����Ϣ
        MusicObject musicObject = new MusicObject();
        musicObject.identify = Utility.generateGUID();
        musicObject.title = mShareMusicView.getTitle();
        musicObject.description = mShareMusicView.getShareDesc();
        
        // ���� Bitmap ���͵�ͼƬ����Ƶ������
        musicObject.setThumbImage(mShareMusicView.getThumbBitmap());
        musicObject.actionUrl = mShareMusicView.getShareUrl();
        musicObject.dataUrl = "www.weibo.com";
        musicObject.dataHdUrl = "www.weibo.com";
        musicObject.duration = 10;
        musicObject.defaultText = "Music Ĭ���İ�";
        return musicObject;*/
       	return null;
    }

    /**
     * ������ý�壨��Ƶ����Ϣ����
     * 
     * @return ��ý�壨��Ƶ����Ϣ����
     */
    private VideoObject getVideoObj() {
    	/*
        // ����ý����Ϣ
        VideoObject videoObject = new VideoObject();
        videoObject.identify = Utility.generateGUID();
        videoObject.title = mShareVideoView.getTitle();
        videoObject.description = mShareVideoView.getShareDesc();
        
        // ���� Bitmap ���͵�ͼƬ����Ƶ������
        videoObject.setThumbImage(mShareVideoView.getThumbBitmap());
        videoObject.actionUrl = mShareVideoView.getShareUrl();
        videoObject.dataUrl = "www.weibo.com";
        videoObject.dataHdUrl = "www.weibo.com";
        videoObject.duration = 10;
        videoObject.defaultText = "Vedio Ĭ���İ�";
        return videoObject;*/
       	return null;
    }

    /**
     * ������ý�壨��Ƶ����Ϣ����
     * 
     * @return ��ý�壨���֣���Ϣ����
     */
    private VoiceObject getVoiceObj() {
    	/*
        // ����ý����Ϣ
        VoiceObject voiceObject = new VoiceObject();
        voiceObject.identify = Utility.generateGUID();
        voiceObject.title = mShareVoiceView.getTitle();
        voiceObject.description = mShareVoiceView.getShareDesc();
        
        // ���� Bitmap ���͵�ͼƬ����Ƶ������
        voiceObject.setThumbImage(mShareVoiceView.getThumbBitmap());
        voiceObject.actionUrl = mShareVoiceView.getShareUrl();
        voiceObject.dataUrl = "www.weibo.com";
        voiceObject.dataHdUrl = "www.weibo.com";
        voiceObject.duration = 10;
        voiceObject.defaultText = "Voice Ĭ���İ�";
        return voiceObject;*/
       	return null;
    }
}
