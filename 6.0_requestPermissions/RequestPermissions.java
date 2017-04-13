	/**
	 * 先判断是否已获取到相关权限。
	 * 没有则申请所需的权限，再根据权限授予的情况做出相应的判断（权限申请失败给出警示）
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
					// 已获得权限，开启背景音乐服务
					Intent intent = new Intent(this, BackgroundMusicService.class);
					bindService(intent, bgmConnection, BIND_AUTO_CREATE);
				} else {
					// 授权失败
					Toast.makeText(this, "Permission Denied，背景音乐将不可用！", Toast.LENGTH_SHORT).show();
				}
				break;
			default:
				super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		}
	}