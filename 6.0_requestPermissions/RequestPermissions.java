	/**
	 * 先判断是否已获取到相关权限。
	 * 没有则申请所需的权限，再根据权限授予的情况做出相应的判断（此方法会继续申请权限，一般情况是提示权限申请失败的相关警示）
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
			// 执行操作
			if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
				if (mPhotoSelectorDomain != null && mPhotoHandler != null) {
					mPhotoSelectorDomain.getExternalStorageAlbums(mPhotoHandler);
				}
			}
		} else if (requestCode == REQUEST_CODE_BACKGROUND_MUSIC) {
			if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
				// 背景音乐服务
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