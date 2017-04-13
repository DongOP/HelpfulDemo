	/**
	 * ���ж��Ƿ��ѻ�ȡ�����Ȩ�ޡ�
	 * û�������������Ȩ�ޣ��ٸ���Ȩ����������������Ӧ���жϣ��˷������������Ȩ�ޣ�һ���������ʾȨ������ʧ�ܵ���ؾ�ʾ��
	 **/
	public boolean isGrantBgmR() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
				checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

			requestPermissions(new String[]{
					Manifest.permission.READ_EXTERNAL_STORAGE,
					Manifest.permission.WRITE_EXTERNAL_STORAGE
			}, REQUEST_CODE_BACKGROUND_MUSIC);

			return false;
		}

		return true;
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);

		if (requestCode == REQUEST_CODE_WRITE_EXTERNAL_STORAGE) {
			// ִ�в���
			if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
				if (mPhotoSelectorDomain != null && mPhotoHandler != null) {
					mPhotoSelectorDomain.getExternalStorageAlbums(mPhotoHandler);
				}
			}
		} else if (requestCode == REQUEST_CODE_BACKGROUND_MUSIC) {
			if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
				// �������ַ���
				Intent intent = new Intent(this, BackgroundMusicService.class);
				bindService(intent, bgmConnection, BIND_AUTO_CREATE);
			}
		} else {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
				requestPermissions(
						new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
						REQUEST_CODE_WRITE_EXTERNAL_STORAGE);
			}
		}
	}