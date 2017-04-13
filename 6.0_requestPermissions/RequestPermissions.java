	/**
	 * ���ж��Ƿ��ѻ�ȡ�����Ȩ�ޡ�
	 * û�������������Ȩ�ޣ��ٸ���Ȩ����������������Ӧ���жϣ�Ȩ������ʧ�ܸ�����ʾ��
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
		
		switch (requestCode){
			case REQUEST_CODE_BACKGROUND_MUSIC:
				if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
					// �ѻ��Ȩ�ޣ������������ַ���
					Intent intent = new Intent(this, BackgroundMusicService.class);
					bindService(intent, bgmConnection, BIND_AUTO_CREATE);
				} else {
					// ��Ȩʧ��
					Toast.makeText(this, "Permission Denied���������ֽ������ã�", Toast.LENGTH_SHORT).show();
				}
				break;
			default:
				super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		}
	}